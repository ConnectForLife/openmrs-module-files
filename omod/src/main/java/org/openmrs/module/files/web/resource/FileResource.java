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
import org.openmrs.module.files.File;
import org.openmrs.module.files.FileCategory;
import org.openmrs.module.files.api.service.FileCategoryService;
import org.openmrs.module.files.api.service.FileService;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.ConversionUtil;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.PropertyGetter;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.RefRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.PageableResult;
import org.openmrs.module.webservices.rest.web.resource.api.Uploadable;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.openmrs.module.webservices.rest.web.response.ResponseException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Resource(name = RestConstants.VERSION_1 + FileResource.RESOURCE_URI, supportedClass = File.class,
    supportedOpenmrsVersions = {"2.*"})
public class FileResource extends DataDelegatingCrudResource<File> implements Uploadable {
  static final String RESOURCE_URI = "/file";

  @Override
  public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
    if (representation instanceof DefaultRepresentation || representation instanceof FullRepresentation) {
      final DelegatingResourceDescription description = new DelegatingResourceDescription();
      description.addProperty("uuid");
      description.addProperty("id");
      description.addProperty("display");
      description.addProperty("filename");
      description.addProperty("mimeType");
      description.addProperty("fileCategory");
      description.addLink("bytes", RestConstants.URI_PREFIX + RestConstants.VERSION_1 + "/" + FileResource.RESOURCE_URI +
          FileBytesResource.GET_FILE_URI);
      return description;
    } else if (representation instanceof RefRepresentation) {
      final DelegatingResourceDescription description = new DelegatingResourceDescription();
      description.addProperty("uuid");
      description.addProperty("display");
      return description;
    }
    return null;
  }

  @Override
  public Object create(SimpleObject propertiesToCreate, RequestContext context) throws ResponseException {
    throw new ResourceDoesNotSupportOperationException("Use POST with Content-Type=multipart/form-data to upload new File.");
  }

  @Override
  public Object upload(MultipartFile multipartFile, RequestContext requestContext) throws ResponseException, IOException {
    final FileCategory fileCategory = Context
        .getService(FileCategoryService.class)
        .getFileCategoryByUuid(requestContext.getParameter(File.FILE_CATEGORY_PROP));

    File newFile = newDelegate();
    newFile.setFileCategory(fileCategory);
    newFile.setFilename(requestContext.getParameter(File.FILENAME_PROP));
    newFile.setMimeType(requestContext.getParameter(File.MIME_TYPE_PROP));
    newFile.setFileData(multipartFile.getBytes());
    newFile = Context.getService(FileService.class).saveFile(newFile);
    return ConversionUtil.convertToRepresentation(newFile, Representation.DEFAULT);
  }

  @Override
  public File newDelegate() {
    return new File();
  }

  @Override
  public File save(File file) {
    return Context.getService(FileService.class).saveFile(file);
  }

  @Override
  public File getByUniqueId(String uuid) {
    return Context.getService(FileService.class).getFileByUuid(uuid);
  }

  @Override
  public void purge(File file, RequestContext requestContext) throws ResponseException {
    if (file != null) {
      Context.getService(FileService.class).purgeFile(file);
    }
  }

  @Override
  protected void delete(File file, String reason, RequestContext requestContext) throws ResponseException {
    if (!Boolean.TRUE.equals(file.getVoided())) {
      Context.getService(FileService.class).voidFile(file, reason);
    }
  }

  @Override
  protected PageableResult doGetAll(RequestContext context) throws ResponseException {
    final List<File> files = Context.getService(FileService.class).getAllFiles(context.getIncludeAll());
    return new NeedsPaging<>(files, context);
  }

  @PropertyGetter("display")
  public String getDisplay(File delegate) {
    return delegate.getFilename();
  }
}
