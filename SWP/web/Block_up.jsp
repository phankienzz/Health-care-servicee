<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Upload Bài Báo</title>
    <style>
        body { font-family: Arial, sans-serif; }
        .container { max-width: 600px; margin: auto; padding: 20px; }
        input[type="text"], input[type="file"] { width: 100%; margin-bottom: 10px; }
        input[type="submit"] { background-color: #4CAF50; color: white; border: none; padding: 10px; cursor: pointer; }
        input[type="submit"]:hover { background-color: #45a049; }
    </style>
</head>
<body>
<div class="container">
    <h2>Upload Bài Báo</h2>
    <form action="uploadServlet" method="post" enctype="multipart/form-data">
        <label for="title">Tiêu đề:</label>
        <input type="text" id="title" name="title" required>

        <label for="author">Tác giả:</label>
        <input type="text" id="author" name="author" required>

        <label for="file">Tệp Bài Báo:</label>
        <input type="file" id="file" name="file" accept=".pdf,.doc,.docx" required>

        <input type="submit" value="Upload">
    </form>
</div>
</body>
</html>