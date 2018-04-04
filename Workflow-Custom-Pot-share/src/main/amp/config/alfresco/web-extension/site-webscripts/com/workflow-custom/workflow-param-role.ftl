<#include "/org/alfresco/components/form/controls/common/utils.inc.ftl" />
<div class="form-field">
   <#if form.mode == "view" || (form.mode == "edit" && field.disabled)>
      <div class="viewmode-field">
         <span class="viewmode-label">${field.label?html}:</span>
         <span class="viewmode-value">
         <#if field.value?string == "Manager">${msg("Manager")}
         <#elseif field.value?string == "Collaborator">${msg("Collaborator")}
         <#elseif field.value?string == "Contributor">${msg("Contributor")}
         <#else>${field.value?html}</#if>
         </span>
      </div>
   <#else>
      <label for="${fieldHtmlId}">${field.label?html}:<#if field.mandatory><span class="mandatory-indicator">${msg("form.required.fields.marker")}</span></#if></label>
      <select id="${fieldHtmlId}" name="${field.name}" tabindex="0" size="1"
            <#if field.description??>title="${field.description}"</#if>
            <#if field.control.params.styleClass??>class="${field.control.params.styleClass}"</#if>
            <#if field.control.params.style??>style="${field.control.params.style}"</#if>
            <#if field.disabled>disabled="true"</#if>>
            <option value="Manager"<#if field.value?string == "Manager"> selected="selected"</#if>>${msg("Manager")}</option>
            <option value="Collaborator"<#if field.value?string == "Collaborator"> selected="selected"</#if>>${msg("Collaborator")}</option>
            <option value="Contributor"<#if field.value?string == "Contributor"> selected="selected"</#if>>${msg("Contributor")}</option>
      </select>
   </#if>
</div>






