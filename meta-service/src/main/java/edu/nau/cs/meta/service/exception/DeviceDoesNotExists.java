package edu.nau.cs.meta.service.exception;

public class DeviceDoesNotExists extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "Device with deviceId = %s for userId = %s";

    public DeviceDoesNotExists(String deviceId, String userId) {
        super(String.format(MESSAGE_TEMPLATE, deviceId, userId));
    }

}
