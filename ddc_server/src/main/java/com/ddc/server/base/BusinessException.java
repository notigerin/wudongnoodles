package com.ddc.server.base;

/**
 * @author dingpengfei
 * @since on 2018/5/8.
 */
public class BusinessException extends Exception {
  /** Comment for &lt;code&gt;serialVersionUID&lt;/code&gt; */
  private static final long serialVersionUID = 3455708526465670030L;

  public BusinessException(String msg) {
    super(msg);
  }
}
