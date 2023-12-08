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
import org.openmrs.module.files.api.service.FileService;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.response.GenericRestException;
import org.openmrs.module.webservices.rest.web.response.IllegalRequestException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping(value = "/rest/" + RestConstants.VERSION_1 + "/" + FileResource.RESOURCE_URI)
public class FileBytesResource {
  static final String GET_FILE_URI = "/{uuid}/bytes";

  @RequestMapping(value = GET_FILE_URI, method = RequestMethod.GET)
  public void getFile(@PathVariable("uuid") String uuid, HttpServletResponse response) {
    final File file = Context.getService(FileService.class).getFileByUuid(uuid);

    if (file == null) {
      throw new IllegalRequestException("File not found for uuid: " + uuid);
    }

    try {
      response.setContentType(Optional.ofNullable(file.getMimeType()).orElse("application/octet-stream"));
      response.addHeader("Content-Disposition", "attachment; filename=" + file.getFilename());
      response.getOutputStream().write(file.getFileData());
    } catch (IOException ex) {
      response.setStatus(500);
      throw new GenericRestException("There was an error when downloading file with uuid: " + uuid, ex);
    }
  }
}
