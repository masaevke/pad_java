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
 * UpdateSubscriberRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.12.0")
public class UpdateSubscriberRequest {

  private JsonNullable<@Size(max = 128) String> name = JsonNullable.<String>undefined();

  private JsonNullable<@Pattern(regexp = "^([01][0-9]|2[0-3]):[0-5][0-9]$") String> scheduleTime = JsonNullable.<String>undefined();

  private JsonNullable<Boolean> active = JsonNullable.<Boolean>undefined();

  public UpdateSubscriberRequest name(String name) {
    this.name = JsonNullable.of(name);
    return this;
  }

  /**
   * Get name
   * @return name
   */
  @Size(max = 128) 
  @Schema(name = "name", example = "Старший диспетчер", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public JsonNullable<@Size(max = 128) String> getName() {
    return name;
  }

  public void setName(JsonNullable<String> name) {
    this.name = name;
  }

  public UpdateSubscriberRequest scheduleTime(String scheduleTime) {
    this.scheduleTime = JsonNullable.of(scheduleTime);
    return this;
  }

  /**
   * Get scheduleTime
   * @return scheduleTime
   */
  @Pattern(regexp = "^([01][0-9]|2[0-3]):[0-5][0-9]$") 
  @Schema(name = "schedule_time", example = "08:00", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("schedule_time")
  public JsonNullable<@Pattern(regexp = "^([01][0-9]|2[0-3]):[0-5][0-9]$") String> getScheduleTime() {
    return scheduleTime;
  }

  public void setScheduleTime(JsonNullable<String> scheduleTime) {
    this.scheduleTime = scheduleTime;
  }

  public UpdateSubscriberRequest active(Boolean active) {
    this.active = JsonNullable.of(active);
    return this;
  }

  /**
   * Get active
   * @return active
   */
  
  @Schema(name = "active", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("active")
  public JsonNullable<Boolean> getActive() {
    return active;
  }

  public void setActive(JsonNullable<Boolean> active) {
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
    UpdateSubscriberRequest updateSubscriberRequest = (UpdateSubscriberRequest) o;
    return equalsNullable(this.name, updateSubscriberRequest.name) &&
        equalsNullable(this.scheduleTime, updateSubscriberRequest.scheduleTime) &&
        equalsNullable(this.active, updateSubscriberRequest.active);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(hashCodeNullable(name), hashCodeNullable(scheduleTime), hashCodeNullable(active));
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
    sb.append("class UpdateSubscriberRequest {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

