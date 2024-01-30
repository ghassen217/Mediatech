package com.fstg.mediatech.avromodel;

import com.fstg.mediatech.client.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientAvro extends Client {
    private Date createdDate;
    private Date modifiedDate;

}
