<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="collapse mt-3" id="collapseExample">
        <div class="input-group">
            <form method="post" enctype="multipart/form-data" class="form-control" >
                <input type="text"  class="form-control mt-3" name="newTagName" placeholder="Введите новую категорию" required>
            </form>
        </div>
    </div>
</@c.page>