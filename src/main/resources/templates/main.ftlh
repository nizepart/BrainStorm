<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
<div>
    <div>
        <@l.logout/>
        <span><a href="/user">User list</a></span>
    </div>
    <form method="post" enctype="multipart/form-data">
        <input type="text" name="title" placeholder="Название статьи">
        <input type="text" name="full_text" placeholder="Введите текст">
        <input type="file" name="file">
        <button type="submit">Добавить</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</div>
<form method="get" action="/main">
    <input type="text" name="filter" value="${filter?ifExists}">
    <button type="submit">Найти</button>
</form>
<div>Лента</div>
<#list blog as post>
     <div>
         <b>${post.id}</b>
         <i>${post.title}</i>
         <span>${post.full_text}</span>
         <strong>${post.authorName}</strong>
         <div>
             <#if post.filename??>
                 <img src="/img/${post.filename}">
             </#if>
         </div>
         <div>
             <span><a href="/comments/${post.id}">Комментарии</a></span>
         </div>
     </div>
    <#else>
    No posts
</#list>
</@c.page>