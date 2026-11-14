package ru.urfu.petrovich.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.lang.Nullable;
import java.util.NoSuchElementException;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CreateSubscriberRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.12.0")
public class CreateSubscriberRequest {

  private Long chatId;

  private JsonNullable<@Size(max = 128) String> name = JsonNullable.<String>undefined();

  private String scheduleTime = "09:30";

  public CreateSubscriberRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateSubscriberRequest(Long chatId) {
    this.chatId = chatId;
  }

  public CreateSubscriberRequest chatId(Long chatId) {
    this.chatId = chatId;
    return this;
  }

  /**
   * Telegram chat_id; для групп может быть отрицательным.
   * @return chatId
   */
  @NotNull 
  @Schema(name = "chat_id", example = "-1001234567890", description = "Telegram chat_id; для групп может быть отрицательным.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("chat_id")
  public Long getChatId() {
    return chatId;
  }

  public void setChatId(Long chatId) {
    this.chatId = chatId;
  }

  public CreateSubscriberRequest name(String name) {
    this.name = JsonNullable.of(name);
    return this;
  }

  /**
   * Get name
   * @return name
   */
  @Size(max = 128) 
  @Schema(name = "name", example = "Диспетчер автопарка", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public JsonNullable<@Size(max = 128) String> getName() {
    return name;
  }

  public void setName(JsonNullable<String> name) {
    this.name = name;
  }

  public CreateSubscriberRequest scheduleTime(String scheduleTime) {
    this.scheduleTime = scheduleTime;
    return this;
  }

  /**
   * Время ежедневной рассылки, HH:MM, 24-часовой формат.
   * @return scheduleTime
   */
  @Pattern(regexp = "^([01][0-9]|2[0-3]):[0-5][0-9]$") 
  @Schema(name = "schedule_time", example = "09:30", description = "Время ежедневной рассылки, HH:MM, 24-часовой формат.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("schedule_time")
  public String getScheduleTime() {
    return scheduleTime;
  }

  public void setScheduleTime(String scheduleTime) {
    this.scheduleTime = scheduleTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateSubscriberRequest createSubscriberRequest = (CreateSubscriberRequest) o;
    return Objects.equals(this.chatId, createSubscriberRequest.chatId) &&
        equalsNullable(this.name, createSubscriberRequest.name) &&
        Objects.equals(this.scheduleTime, createSubscriberRequest.scheduleTime);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(chatId, hashCodeNullable(name), scheduleTime);
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateSubscriberRequest {\n");
    sb.append("    chatId: ").append(toIndentedString(chatId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    scheduleTime: ").append(toIndentedString(scheduleTime)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

