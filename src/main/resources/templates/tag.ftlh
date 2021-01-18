<#import "parts/common.ftlh" as c>
<@c.page>
    <div>
        List of tags
    </div>
    <#list tags as tag>
        <div>
            <b>${tag.name}</b>
        </div>
    <#else>
        <div>Тегов нет</div>
    </#list>
    <div>
        <form method="post">
            <input type="text" name="newTagName" placeholder="Введите новый тег">
            <button type="submit">Отправить</button>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
        </form>
    </div>
</@c.page>