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

    <#if isAdmin>
    <a href="/edit" type="button" class="btn btn-outline-light">Редактировать теги</a>
    </#if>

    <#include "parts/postEdit.ftlh"/>

    <#include "parts/postList.ftlh"/>

</@c.page>