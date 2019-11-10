<#list articles as article>
    <div>
        <a href="/${article.id}">${article.title}</a>
        <h5>${article.shortDescription}</h5>
    </div>
</#list>

