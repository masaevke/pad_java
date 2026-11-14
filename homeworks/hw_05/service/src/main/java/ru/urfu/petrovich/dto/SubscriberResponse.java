package ru.urfu.petrovich.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * SubscriberResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.12.0")
public class SubscriberResponse {

  private Long id;

  private Long chatId;

  private JsonNullable<@Size(max = 128) String> name = JsonNullable.<String>undefined();

  private JsonNullable<@Size(max = 64) String> username = JsonNullable.<String>undefined();

  private String scheduleTime;

  private Boolean active;

  public SubscriberResponse() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SubscriberResponse(Long id, Long chatId, String name, String username, String scheduleTime, Boolean active) {
    this.id = id;
    this.chatId = chatId;
    this.name = JsonNullable.of(name);
    this.username = JsonNullable.of(username);
    this.scheduleTime = scheduleTime;
    this.active = active;
  }

  public SubscriberResponse id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * minimum: 1
   * @return id
   */
  @NotNull @Min(1L) 
  @Schema(name = "id", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public SubscriberResponse chatId(Long chatId) {
    this.chatId = chatId;
    return this;
  }

  /**
   * Get chatId
   * @return chatId
   */
  @NotNull 
  @Schema(name = "chat_id", example = "-1001234567890", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("chat_id")
  public Long getChatId() {
    return chatId;
  }

  public void setChatId(Long chatId) {
    this.chatId = chatId;
  }

  public SubscriberResponse name(String name) {
    this.name = JsonNullable.of(name);
    return this;
  }

  /**
   * Get name
   * @return name
   */
  @NotNull @Size(max = 128) 
  @Schema(name = "name", example = "Диспетчер автопарка", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public JsonNullable<@Size(max = 128) String> getName() {
    return name;
  }

  public void setName(JsonNullable<String> name) {
    this.name = name;
  }

  public SubscriberResponse username(String username) {
    this.username = JsonNullable.of(username);
    return this;
  }

  /**
   * Telegram username, если он известен сервису.
   * @return username
   */
  @NotNull @Size(max = 64) 
  @Schema(name = "username", example = "dispatcher", description = "Telegram username, если он известен сервису.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("username")
  public JsonNullable<@Size(max = 64) String> getUsername() {
    return username;
  }

  public void setUsername(JsonNullable<String> username) {
    this.username = username;
  }

  public SubscriberResponse scheduleTime(String scheduleTime) {
    this.scheduleTime = scheduleTime;
    return this;
  }

  /**
   * Get scheduleTime
   * @return scheduleTime
   */
  @NotNull @Pattern(regexp = "^([01][0-9]|2[0-3]):[0-5][0-9]$") 
  @Schema(name = "schedule_time", example = "09:30", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("schedule_time")
  public String getScheduleTime() {
    return scheduleTime;
  }

  public void setScheduleTime(String scheduleTime) {
    this.scheduleTime = scheduleTime;
  }

  public SubscriberResponse active(Boolean active) {
    this.active = active;
    return this;
  }

  /**
   * Get active
   * @return active
   */
  @NotNull 
  @Schema(name = "active", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("active")
  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SubscriberResponse subscriberResponse = (SubscriberResponse) o;
    return Objects.equals(this.id, subscriberResponse.id) &&
        Objects.equals(this.chatId, subscriberResponse.chatId) &&
        Objects.equals(this.name, subscriberResponse.name) &&
        Objects.equals(this.username, subscriberResponse.username) &&
        Objects.equals(this.scheduleTime, subscriberResponse.scheduleTime) &&
        Objects.equals(this.active, subscriberResponse.active);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, chatId, name, username, scheduleTime, active);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriberResponse {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    chatId: ").append(toIndentedString(chatId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    scheduleTime: ").append(toIndentedString(scheduleTime)).append("\n");
    sb.append("    active: ").append(toIndentedString(active)).append("\n");
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

