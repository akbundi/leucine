<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up</title>
</head>
<body>
    <h1>Sign Up</h1>
    <form method="post" action="signup">
        <label for="username">Username:</label>

        <input type="text" id="username" name="username">

        <label for="password">Password:</label>

        <input type="password" id="password" name="password">

        <input type="hidden" id="role" name="role" value="Employee">
        <input type="submit" value="Sign Up">
    </form>

</body>
</html>