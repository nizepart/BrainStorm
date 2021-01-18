<#list blog as post>
    <div class="card my-3">
        <div class="card-body">
            <h5 class="card-title">${post.title}</h5>
            <p class="card-text">${post.full_text}</p>
            <p class="card-text"><small class="text-muted">${post.tagName}</small></p>
            <div class="text-muted mb-2">${post.creationDate}</div>
            <div class="card-footer text-muted form-control"><a href="/main/profile/${post.author.id}"> ${post.authorName} </a></div>
            <div>
                <span><a class="btn btn-primary my-2" role="button" href="/comments/${post.id}">Комментарии</a></span>
            </div>
        </div>
        <#if post.filename??>
            <img src="/img/${post.filename}" class="card-img-bottom">
        </#if>
    </div>
<#else>
    <div>No posts</div>
</#list>