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
                        <table class="table">
                            <div class="container">
                                <div class="row align-items-start">
                                    <div class="col"><b class="mx-2 mt-2"><a href="/main/profile/${post.author.id}"> ${post.authorName} </a></b> </div>
                                    <div class="col"><a class="btn btn-primary mx-2 my-1" role="button" href="/comments/${post.id}">Комментарии</a> </div>
                                    <div class="col"><a class="btn btn-primary mx-2 my-1" role="button" href="/like/${post.id}">Like</a> </div>
                                    <div class="col"><a class="btn btn-primary mx-2 my-1" role="button" href="/unlike/${post.id}">Unike</a> </div>
                                    <div class="col"><p class="mx-2 mt-2">${post.likesCount}</p> </div>
                                </div>
                            </div>
                        </table>
                    </div>
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