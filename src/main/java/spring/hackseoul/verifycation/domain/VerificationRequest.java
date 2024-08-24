package spring.hackseoul.verifycation.domain;

import lombok.Getter;

@Getter
public class VerificationRequest {
    private Proof proof;
    private String schemaId;

    @Getter
    public static class Proof {
        private String taskId;
        private String allocatorAddress;
        private String publicFieldsHash;
        private String allocatorSignature;
        private String uHash;
        private String validatorAddress;
        private String validatorSignature;
    }
}