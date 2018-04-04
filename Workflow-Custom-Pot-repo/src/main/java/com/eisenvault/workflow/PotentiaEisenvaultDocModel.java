/**
 * 
 */
/**
 * @author tushar
 *
 */
package com.eisenvault.workflow;

/*
 * Copyright (C) 2005-2010 Alfresco Software Limited.
 *
 * This file is part of Alfresco
 *
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 */
import java.io.Serializable;
import java.util.Map;

import org.alfresco.api.AlfrescoPublicApi;     
import org.alfresco.service.namespace.QName;

import com.eisenvault.workflow.PotwfNamespaceService;


/**
 * Workflow Model Constants
 */
@AlfrescoPublicApi
public interface PotentiaEisenvaultDocModel
{
	
	public static final QName QNAME_EISENVAULT_DOC_CONTENT_MODEL_URI  = QName.createQName(PotwfNamespaceService.POTENTIA_BASE_WORKFLOW_MODEL_URI);
	
	public static final QName ROLE_REQUIRED = QName.createQName(PotwfNamespaceService.POTENTIA_BASE_WORKFLOW_MODEL_URI,"requiredRole");
	
	public static final QName ROLE_REQUIRED_LIST = QName.createQName(PotwfNamespaceService.POTENTIA_BASE_WORKFLOW_MODEL_URI,"siteRoleList");
}