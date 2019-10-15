package com.springboot.quickstart.common;

public enum ErrorCode {

	INPUT_INVALID_PAYLOAD("invalid.request.payload"), INVALID_QUERY_PARAM_VALUE(
			"input.query.param.value"), INVALID_REQUEST_PAYLOAD_DATATYPE(
					"invalid.request.payload.datatype"), RESOURCE_NOT_FOUND("resource.not.found"), MISC_CLIENT_ERROR(
							"misc.client.error"), MISC_SERVER_ERROR("misc.server.error"), AUTHORIZATION_ERROR(
									"authorization.error"), AUTHENTICATION_ERROR(
											"authentication.error"), FORBIDDEN_ERROR(
													"forbidden.error"), REPOSITORY_ERROR(
															"repository.error"), EXTERNAL_SYSTEM_ERROR(
																	"external.system.error"), INVALID_FILE_EXTENSION_ERROR(
																			"invalid.file.extension"), INVALID_FILE_COLUMN(
																					"invalid.file.column.error"), INVALID_FILE(
																							"invalid.file"), ELASTIC_SEARCH_INDEX_ERROR(
																									"elastic.search.index.error"), INVALID_REQUEST_VALUE(
																											"input.request.value");

	private String resourceBundleProperty;

	private ErrorCode(String resourceBundleProperty) {
		this.resourceBundleProperty = resourceBundleProperty;
	}

	public String getResourceBundlePropertyName() {
		return resourceBundleProperty;
	}

}
