<html lang="en">
<head>
    <title>Latest</title>
    <link rel="stylesheet"
           href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<h1>Latest snippets</h1>
<table>
    <tr>
        <th>Date</th>
        <th>Code</th>
    </tr>
    <#list snippets as snippet>
        <tr>
            <td><span id="load_date">${snippet.date}</span></td>
            <td><pre id="code_snippet"><code>${snippet.code}</code></pre></td>
        </tr>
    </#list>
</table>
</body>
</html>