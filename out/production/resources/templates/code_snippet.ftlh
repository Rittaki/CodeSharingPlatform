<html>
<head>
    <title>Code</title>
    <link rel="stylesheet"
           href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
    <span id="load_date" style="color: green">${date}</span>

    <#if "${views_bool}" == "true">
            <span id="views_restriction">${views} more views allowed</span>
    </#if>

    <#if "${time}" != "0">
            <span id="time_restriction">The code will be available for ${time} seconds</span>
    </#if>

    <pre id="code_snippet" style="border: 1px solid black; background-color: lightgrey; width: 500px"><code>${code}</code></pre>
</body>
</html>