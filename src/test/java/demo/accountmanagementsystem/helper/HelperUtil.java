package demo.accountmanagementsystem.helper;

import java.io.InputStream;

import com.openapi.gen.springboot.dto.CreateAccountRequest;
import com.openapi.gen.springboot.dto.CreateAccountResponse;
import com.openapi.gen.springboot.dto.PaymentTransferRequest;
import com.openapi.gen.springboot.dto.PaymentTransferResponse;

import demo.accountmanagementsystem.utils.SerializationHelper;
import org.apache.commons.io.IOUtils;
import static java.nio.charset.StandardCharsets.UTF_8;



public class HelperUtil {
	public static CreateAccountRequest readResourceAsAccountRequest(String resourceName) throws Exception {
        try (InputStream stream = HelperUtil.class.getClassLoader().getResourceAsStream(resourceName)) {
            return SerializationHelper.toObject(IOUtils.toString(stream, UTF_8), CreateAccountRequest.class);
        }
    }

    public static PaymentTransferRequest readResourceAsTransferRequest(String resourceName) throws Exception {
        try (InputStream stream = HelperUtil.class.getClassLoader().getResourceAsStream(resourceName)) {
            return SerializationHelper.toObject(IOUtils.toString(stream, UTF_8), PaymentTransferRequest.class);
        }
    }

    public static CreateAccountResponse readResourceAsAccountResponse(String resourceName)
            throws Exception {
        try (InputStream stream = HelperUtil.class.getClassLoader().getResourceAsStream(resourceName)) {
            return SerializationHelper.toObject(IOUtils.toString(stream, UTF_8), CreateAccountResponse.class);
        }
    }
    
    public static PaymentTransferResponse readResourceAsTransferResponse(String resourceName)
            throws Exception {
        try (InputStream stream = HelperUtil.class.getClassLoader().getResourceAsStream(resourceName)) {
            return SerializationHelper.toObject(IOUtils.toString(stream, UTF_8), PaymentTransferResponse.class);
        }
    }
	
}
