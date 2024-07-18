package org.irmc.industrialrevival.core.message;

import org.irmc.industrialrevival.api.objects.Pair;

public class MessageReplacement extends Pair<String, String> {
  public MessageReplacement(String placeholder, String value) {
    super(placeholder, value);
  }

  public String parse(String message) {
    return message.replace(getA(), getB());
  }
}
