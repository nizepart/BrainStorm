<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="form-row mb-3">
        <div class="form-group col-md-6">
            <form method="get" action="/main" class="row row-cols-lg-auto g-3 align-items-center">
                <div class="col-12">
                    <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by title">
                </div>
                <div class="col-12">
                    <button type="submit" class="btn btn-primary" >Search</button>
                </div>
            </form>
        </div>
    </div>

    <a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Add new Post
    </a>
    <div class="collapse mt-3" id="collapseExample">
        <div class="input-group">
            <form method="post" enctype="multipart/form-data" class="form-control" >
                <input type="text"  class="form-control mt-3" name="title" placeholder="Название статьи">
                <input type="text"  class="form-control mt-3" name="full_text" placeholder="Введите текст">
                <input class="form-control form-control-lg mt-3" id="formFileLg" type="file" name="file">
                <input type="text"  class="form-control mt-3" name="tagName" placeholder="Выберите категорию">
                <button type="submit" class="btn btn-primary mt-3 mb-3">Добавить</button>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
            </form>
        </div>
    </div>

    <#list blog as post>
        <div class="card my-3">
        <div class="card-body">
            <h5 class="card-title">${post.title}</h5>
            <p class="card-text">${post.full_text}</p>
            <p class="card-text"><small class="text-muted">${post.tagName}</small></p>
            <div class="text-muted mb-2">${post.creationDate}</div>
            <div class="card-footer text-muted form-control">${post.authorName}</div>
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
</@c.page>