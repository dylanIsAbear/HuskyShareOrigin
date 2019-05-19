# HuskyShare

## Frontend guide

Frontend: only edit files in `src/main/resources`.


### Important notes
Found any supports files and docs on https://drive.google.com/drive/folders/0ACRawbuRWIiwUk9PVA

### Directory structure
* `css`, `js`, `img`, etc. - static resource files.
* `templates` - html files. They will be parsed by Thymeleaf (explained below).
* `templates/fragments` - html fragments. Not to be served standalone. Only to be included in other html files.

### APIs used
* RESTFUL structure
* JSON when dissolving HTTP request/response
* Shiro as Security manager
