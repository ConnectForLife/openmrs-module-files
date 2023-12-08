/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 *  graphic logo is a trademark of OpenMRS Inc.
 */

package org.openmrs.module.files.web.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.files.FileCategory;
import org.openmrs.module.files.api.service.FileCategoryService;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.resource.api.PageableResult;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.MetadataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

import java.util.List;

@Resource(name = RestConstants.VERSION_1 + FileCategoryResource.RESOURCE_URI, supportedClass = FileCategory.class,
    supportedOpenmrsVersions = {"2.*"})
public class FileCategoryResource extends MetadataDelegatingCrudResource<FileCategory> {
  static final String RESOURCE_URI = "/fileCategory";

  @Override
  public DelegatingResourceDescription getCreatableProperties() {
    final DelegatingResourceDescription description = new DelegatingResourceDescription();
    description.addRequiredProperty("name");
    description.addProperty("description");
    return description;
  }

  @Override
  public FileCategory newDelegate() {
    return new FileCategory();
  }

  @Override
  public FileCategory save(FileCategory category) {
    return Context.getService(FileCategoryService.class).saveFileCategory(category);
  }

  @Override
  public FileCategory getByUniqueId(String uuid) {
    return Context.getService(FileCategoryService.class).getFileCategoryByUuid(uuid);
  }

  @Override
  public void purge(FileCategory category, RequestContext requestContext) throws ResponseException {
    if (category != null) {
      Context.getService(FileCategoryService.class).purgeFileCategory(category);
    }
  }

  @Override
  public void delete(FileCategory delegate, String reason, RequestContext context) throws ResponseException {
    super.delete(delegate, reason, context);
  }

  @Override
  protected PageableResult doGetAll(RequestContext context) throws ResponseException {
    final List<FileCategory> fileCategories =
        Context.getService(FileCategoryService.class).getAllFileCategories(context.getIncludeAll());
    return new NeedsPaging<>(fileCategories, context);
  }

  @Override
  public String getResourceVersion() {
    return "2.3";
  }
}
