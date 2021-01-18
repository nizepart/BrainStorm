<#import "parts/common.ftlh" as c>

<@c.page>
    User Editor
    <div class="input-group">
    <form action="/user" method="post" class="form-control">
        <input type="text" name="username" class="form-control mt-3" value="${user.username}">
        <#list roles as role>
            <div class="form-check">
                <input class="form-check-label" type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", " ")}>
                <label class="form-check-label" for="flexCheckDefault">${role}</label>
            </div>
        </#list>
        <input type="hidden" value="${user.id}" name="userId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button type="submit" class="btn btn-primary mt-3 mb-3">Save</button>
    </form>
    </div>
</@c.page>