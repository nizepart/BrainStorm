<a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Post
</a>
<div class="collapse mt-3" id="collapseExample">
    <div class="input-group">
        <form method="post" enctype="multipart/form-data" class="form-control" >
            <input type="text"  class="form-control mt-3" name="title" placeholder="Название статьи">
            <input type="text"  class="form-control mt-3" name="full_text" placeholder="Введите текст" required>
            <input class="form-control form-control-lg mt-3" id="formFileLg" type="file" name="file">
            <input type="text"  class="form-control mt-3" name="tagName" placeholder="Выберите категорию" required>
            <button type="submit" class="btn btn-primary mt-3 mb-3">Save post</button>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="hidden" name="id" value="<#if post??>${post.id}</#if>">
        </form>
    </div>
</div>