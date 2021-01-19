<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>
    <h3>${username}</h3>
    <#if !isCurrentUser>
            <a class="btn btn-info" href="/main/profile/${userChannel.id}/unsubscribe">Unsubscribe</a>
            <a class="btn btn-info" href="/main/profile/${userChannel.id}/subscribe">Subscribe</a>
    </#if>
    <div class="container my-3">
        <div class="col class mb-3">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Subscriptions</h5>
                    <h3 class="card-text">
                        <a href="/main/profile/${userChannel.id}/subscriptions">${subscriptionsCount}</a>
                    </h3>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Subscribers</h5>
                    <h3 class="card-text">
                        <a href="/main/profile/${userChannel.id}/subscribers">${subscribersCount}</a>
                    </h3>
                </div>
            </div>
        </div>
    </div>
    <#if isCurrentUser>
        <div>
            <span><a class="btn btn-primary my-2" role="button" href="/main/profile/${currentUserId}/edit">Edit profile</a></span>
        </div>
        <#include "parts/postEdit.ftlh"/>
    </#if>
    <#include "parts/postList.ftlh"/>
</@c.page>