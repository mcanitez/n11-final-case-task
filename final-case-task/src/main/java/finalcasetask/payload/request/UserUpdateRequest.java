package finalcasetask.payload.request;

public record UserUpdateRequest (Long id,
                                 String name,
                                 String lastName,
                                 String email,
                                 String phoneNumber,
                                 Double latitude,
                                 Double longitude) {
}
