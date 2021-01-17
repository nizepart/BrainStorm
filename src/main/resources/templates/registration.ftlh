<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
Add new user
${message?ifExists}
<@l.register "/registration" />
</@c.page>