package org.lanqiao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.FORBIDDEN,reason="����Խ��222!!!")
public class MyArrayIndexOutofBoundsException extends Exception {//�Զ����쳣

}
