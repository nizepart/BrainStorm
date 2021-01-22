<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row mb-2">
            <label class="col-sm-2 col-form-label"> Username: </label>
            <div class="col-sm-6">
                <input type="text" name="username" class="form-control" required placeholder="Username"/>
            </div>
        </div>
        <#if isRegisterForm>
            <div class="form-group row mb-2">
                <label class="col-sm-2 col-form-label"> First name: </label>
                <div class="col-sm-6">
                    <input type="text" name="first_name" class="form-control" required placeholder="First Name"/>
                </div>
            </div>
            <div class="form-group row mb-2">
                <label class="col-sm-2 col-form-label"> Last name: </label>
                <div class="col-sm-6">
                    <input type="text" name="last_name" class="form-control" required placeholder="Last Name"/>
                </div>
            </div>
        </#if>
        <div class="form-group row mb-5">
            <label class="col-sm-2 col-form-label"> Password:</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" required placeholder="Password"/>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary"><#if isRegisterForm>Create<#else>Sign in</#if></button>
        <#if !isRegisterForm><a class="btn btn-primary" href="/registration" role="button">Add new user</a></#if>
    </form>
</#macro>


<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary">Sign Out</button>
    </form>
</#macro>