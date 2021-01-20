<#include "security.ftlh">

<div class="row row-cols-1 row-cols-md-2 g-4">
    <#list blog as post>
        <div class="col">
            <div class="card my-5">
                <div class="card-body ">
                    <h5 class="card-title">${post.title}</h5>
                    <p class="card-text">${post.full_text}</p>
                    <p class="card-text"><a href="/category/${post.tag.id}">${post.tagName}</a></p>
                    <p class="card-text"><a href="${post.link}" class="text-warning">Source link</a></p>
                    <div class="text-muted mb-2">${post.creationDate}</div>
                    <div class="card-footer text-muted form-control">
                        <b><a href="/main/profile/${post.author.id}"> ${post.authorName} </a></b>
                    </div>
                    <div>
                        <span><a class="btn btn-primary my-3" role="button" href="/comments/${post.id}">Комментарии</a></span>
                    </div>
                    <div>
                        <span><a class="btn btn-primary my-3" role="button" href="/like/${post.id}">Like</a></span>
                    </div>
                    <div>
                        <span><a class="btn btn-primary my-3" role="button" href="/unlike/${post.id}">Unike</a></span>
                    </div>
                    <p>${post.likesCount}</p>
                </div>
                <#if post.filename??>
                    <img src="/img/${post.filename}" class="card-img-bottom rounded">
                </#if>
            </div>
        </div>
    <#else>
        <div>No posts</div>
    </#list>
</div>