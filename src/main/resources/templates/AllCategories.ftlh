<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="row row-cols-1 row-cols-md-3 g-4">
    <#list tags as tag>
    <div class="col">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title"><a href="/category/${tag.id}" class="btn btn-outline-secondary stretched-link form-control">${tag.getName()}</a></h5>
            </div>
        </div>
    </div>
        </#list>
    </div>
</@c.page>