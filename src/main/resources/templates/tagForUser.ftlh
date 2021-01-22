<#import "parts/common.ftlh" as c>
<@c.page>
    <h5 class="mb-3">
        Add tag to approval
    </h5>
    <div>
        <form method="post" class="mt-3">
            <div class="form-group row mb-2">
                <div class="col-sm-6">
                    <input type="text"  class="form-control" name="newTagName" placeholder="Введите новый тег">
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Отправить</button>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
        </form>
    </div>
</@c.page>