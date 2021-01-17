<#import "parts/common.ftlh" as c>

<@c.page>
    <div>
        List of comments
    </div>
    <#list comments as comment>
        <div>
            <b>${comment.authorName}</b>
            <i>${comment.full_text}</i>
            <i>${comment.creationDate}</i>
        </div>
    <#else>
        <div>Комментариев нет</div>
    </#list>
    <div>Left your comment</div>
    <div>
        <form method="post">
            <input type="text" name="full_text" placeholder="Введите комментарий">
            <button type="submit">Отправить</button>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
        </form>
    </div>
</@c.page>