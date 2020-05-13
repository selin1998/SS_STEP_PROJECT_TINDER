
<!doctype html>
<#--<html lang="en">-->
<#--<head>-->
<#--    <meta charset="utf-8">-->
<#--    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">-->
<#--    <meta name="description" content="">-->
<#--    <meta name="author" content="">-->
<#--    <title>Signin Template for Bootstrap</title>-->

<#--    <!-- Bootstrap core CSS &ndash;&gt;-->
<#--    <link href="/static/css/bootstrap.min.css" rel="stylesheet">-->

<#--    <!-- Custom styles for this template &ndash;&gt;-->
<#--    <link rel="stylesheet" href="/static/css/style.css">-->
<#---->
<#--</head>-->

<#--<body class="text-center">-->
<#--<form method="post" class="form-signin">-->
<#--    <img class="mb-4" src="https://cdn.onlinewebfonts.com/svg/img_382078.png" alt="" width="72" height="72">-->
<#--    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>-->
<#--    <label for="inputEmail" class="sr-only">Email address</label>-->
<#--    <input type="email" id="inputEmail" class="form-control" placeholder="Email address" name="email" required autofocus>-->
<#--    <label for="inputPassword" class="sr-only">Password</label>-->
<#--    <input type="password" id="inputPassword" class="form-control" placeholder="Password" name="password" required>-->
<#--    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button><br>-->
<#--    ${message!''}-->
<#--    <label><a href="/register">Don't you have an account? Click here to register.</a></label>-->
<#--    <p class="mt-5 mb-3 text-muted">&copy; Tinder 2020</p>-->
<#--</form>-->
<#--</body>-->
<#--</html>-->

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Sign in</title>

    <#include "css/bootstrap.min.css">
    <#include "css/style.css">

</head>

<body class="text-center">

<#--action=${root}-->
<form class="form" method="post">
    <h1 class="h3 mb-3 font-weight-normal">${message}</h1>
    <#list fields as field>
        <label for=${field} class="sr-only">${field}</label>
        <input class="form-control" type="text" name=${field} placeholder=${field}>
    </#list>
    <label for="inputPassword" class="sr-only">Password</label>
    <input type="password" name="Password" userId="inputPassword" class="form-control" placeholder="Password">
    <input class="btn btn-lg btn-primary btn-block"  class="submit" type="submit" value="Login">
    <div class="or">or</div>
<#--    <#if rout = "/login">-->
<#--        <a href="/reg">Sign up</a>-->
<#--    <#else>-->
<#--        <a href="/login">Sign in</a>-->
<#--    </#if>-->
    <label><a href="/login/signup">Don't have account yet? Click to register.</a></label>
    <p class="mt-5 mb-3 text-muted">&copy; Tinder 2018</p>
</form>

</body>
</html>