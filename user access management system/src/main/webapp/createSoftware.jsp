<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Software</title>
</head>
<body>
    <h1>Create Software</h1>
    <form method="post" action="software">
        <label for="softwareName">Software Name:</label><br>

        <input type="text" id="softwareName" name="softwareName"><br>

        <label for="description">Description:</label><br>

        <textarea id="description" name="description"></textarea><br>

        <label for="accessLevels">Access Levels:</label><br>

        <input type="checkbox" id="read" name="accessLevels" value="Read">Read

        <input type="checkbox" id="write" name="accessLevels" value="Write">Write

        <input type="checkbox" id="admin" name="accessLevels" value="Admin">Admin<br>

        <input type="submit" value="Create Software">
    </form>
</body>
</html>
		