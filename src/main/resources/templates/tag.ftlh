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
            <#list utags as utag>
            <div class="form-check">
                <input class="form-check-label" type="checkbox" name="newTagName" value="${utag.name}">
                <label class="form-check-label">${utag.name}</label>
            </div>
            <#else>
                <div>Тегов для одобрения нет</div>
            </#list>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-primary">Одобрить</button>
        </form>
    </div>
</@c.page>