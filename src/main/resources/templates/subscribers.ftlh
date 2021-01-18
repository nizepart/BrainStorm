<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>
    <h3>${userChannel.getUsername()}</h3>
    <ul class="list-group">
    <#list users as user>
        <li class="list-group-item">
            <a href="/main/profile/${user.id}">${user.getUsername()}</a>
        </li>
    </#list>
    </ul>
</@c.page>