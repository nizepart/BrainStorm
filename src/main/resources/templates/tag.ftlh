<#import "parts/common.ftlh" as c>
<@c.page>
    <h5 class="mb-3">
        List of tags
    </h5>
    <ul class="list-group">
    <#list tags as tag>
        <li class="list-group-item list-group-item-secondary mb-3">
            <b>${tag.name}</b>
        </li>
    <#else>
        <div>Тегов нет</div>
    </#list>
    </ul>
    <div>
        <form method="post" class="mt-3">
            <div class="form-group row mb-2">
                <div class="col-sm-6">
                    <input type="text" class="form-control" name="newTagName" placeholder="Введите новый тег">
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Отправить</button>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
        </form>
    </div>
</@c.page>