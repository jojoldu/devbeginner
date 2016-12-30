<a href="/login">Facebook</a>
<form action="/logout" method="post">
    <button type="submit">logout</button>
    <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">-->
</form>
<a href="/user">User</a> <br/>
<a href="/me">Me</a>
<form action="/h2-console" method="post">
    <button type="submit">h2-console</button>
    <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">-->
</form>