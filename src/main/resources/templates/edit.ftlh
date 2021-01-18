<#import "parts/common.ftlh" as c>

<@c.page>
    <h5>${username}</h5>
    <form method="post">
        <div class="form-group row mb-5">
            <label class="col-sm-2 col-form-label"> Password:</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Password"/>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</@c.page>