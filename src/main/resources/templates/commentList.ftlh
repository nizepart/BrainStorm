<#import "parts/common.ftlh" as c>

<@c.page>
    <h5 mb-3>
        List of comments
    </h5>
    <ul class="list-group">
        <#list comments as comment>
            <li class="list-group-item list-group-item-secondary mb-3">
                <b>${comment.authorName}</b>
                <i>${comment.full_text}</i>
                <i>${comment.creationDate}</i>
            </li>
        <#else>
            <div>Комментариев нет</div>
        </#list>
    </ul>
    <form method="post" class="mt-5">
        <div class="form-group row mb-2">
            <div class="col-sm-6">
                <input type="text" class="form-control" name="full_text" placeholder="Введите комментарий">
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Отправить</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>

</@c.page>