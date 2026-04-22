package com.example.springapi.core.boundary.output.instance;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstanceStatusUseCaseOutput {

    private Boolean connected;
    private String created;
    private Boolean smartphoneConnected;
    private String error;
}
