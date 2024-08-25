package spring.hackseoul.verifycation.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

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
        @JsonProperty("uHash")
        private String uHash;
        private String validatorAddress;
        private String validatorSignature;
    }
}