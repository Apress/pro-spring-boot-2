# Errata for *Book Title*

On **page 98** [JsonInclude.Include.NON_EMPTY detail is false]:
 
Text reads: _It uses an extra @JsonInclude annotation, which says that even if the errors field is empty, it must be included._ The explanation should say the exact opposite, `errors` will not be included in JSON serialization by Jackson. Reference is [here](https://fasterxml.github.io/jackson-annotations/javadoc/2.7/com/fasterxml/jackson/annotation/JsonInclude.Include.html#NON_EMPTY).

***

On **page xx** [Summary of error]:
 
Details of error here. Highlight key pieces in **bold**.

***

On **page xx** [Summary of error]:
 
Details of error here. Highlight key pieces in **bold**.

***
