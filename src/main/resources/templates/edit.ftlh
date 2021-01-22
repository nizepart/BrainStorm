<#import "parts/common.ftlh" as c>

<@c.page>
    <h5>${username}</h5>
    <form method="post">
        <div class="form-group row mb-2">
            <label class="col-sm-2 col-form-label"> Password:</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Enter your or new password"/>
            </div>
        </div>
        <div class="form-group row mb-2">
            <label class="col-sm-2 col-form-label"> First name: </label>
            <div class="col-sm-6">
                <input type="text" name="first_name" class="form-control" value="${user.first_name}"/>
            </div>
        </div>
        <div class="form-group row mb-2">
            <label class="col-sm-2 col-form-label"> Last name: </label>
            <div class="col-sm-6">
                <input type="text" name="last_name" class="form-control" required value="${user.last_name}">
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</@c.page>