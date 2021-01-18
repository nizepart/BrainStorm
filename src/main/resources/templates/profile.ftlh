<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>
    <h5>${username}</h5>
    <div>
        <span><a class="btn btn-primary my-2" role="button" href="/main/profile/${currentUserId}/edit">Edit profile</a></span>
    </div>
<#if isCurrentUser>
    <#include "parts/postEdit.ftlh"/>
</#if>
    <#include "parts/postList.ftlh"/>
</@c.page>