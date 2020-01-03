package news.blog.com.messagesender.email;

import lombok.AllArgsConstructor;

import news.blog.com.repository.UserRepository;
import news.blog.com.messagesender.MessageSender;
import news.blog.com.messagesender.email.model.BaseModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.mail.MailException;
import org.springframework.stereotype.Component;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.annotation.Resource;

import freemarker.template.Template;
import freemarker.template.Configuration;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

@Component
@AllArgsConstructor
public class EmailMessageSender implements MessageSender
{

    private final Logger logger = LoggerFactory.getLogger(EmailMessageSender.class);

    private static final String MODEL = "model";
    private static final String SENDER = "NEWS-BLOG";

    private static final String NEW_ARTICLE_TEMPLATE = "new-article.ftl";
    private static final String NEW_ARTICLE_SUBJECT = "Create new article";
    private static final String MESSAGE_ARTICLE = "Create new article with title `%s`";

    @Resource
    private JavaMailSender javaMailSender;

    @Resource
    private Configuration configuration;

    private final UserRepository userRepository;


    private void sendBaseEmail(String email, String message, String subject, String template)
    {
        BaseModel baseModel = new BaseModel();
        baseModel.setMessage(message);

        Map<String, Object> model = new HashMap<>();
        model.put(MODEL, baseModel);

        send(email, template, subject, model);
    }

    private void send(String email, String template, String subject, Map<String, Object> model)
    {
        MimeMessagePreparator preparator = mimeMessage ->
        {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setTo(email);
            message.setFrom("test@mail.ru", SENDER);
            message.setSubject(subject);
            message.setText(getTemplateContent(template, model));
        };
        try
        {
            javaMailSender.send(preparator);
        }
        catch (MailException e)
        {
            logger.error("Mail was not sent: {}", e.getMessage());
        }
    }

    private String getTemplateContent(String templateName, Map<String, Object> model)
    {
        try
        {
            Template template = configuration.getTemplate(templateName);
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        }
        catch (Exception e)
        {
            logger.error("Exception occurred while processing template: {}", e.getMessage());
        }
        return "";
    }

    @Override
    public void sendLetterUsers(String titleArticle)
    {
        String message = String.format(MESSAGE_ARTICLE, titleArticle);
        List<String> emails = userRepository.findAllEmails();

        emails.forEach(email -> {
            sendBaseEmail(email, message, NEW_ARTICLE_SUBJECT, NEW_ARTICLE_TEMPLATE);
        });

    }
}
