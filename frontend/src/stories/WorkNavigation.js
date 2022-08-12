import React from "react";
import {
  Button,
  Switch,
  FormGroup,
  FormControlLabel,
  Box,
  TextField,
} from "@mui/material";


const ToggleLabel = ({ onChange, checked, disabled }) => {
  return (
    <FormControlLabel
    control={<Switch checked={checked} onChange={onChange} disabled={disabled} />}
    label="Track Progress"
    labelPlacement="start"
    sx={{ mx: "auto" }}
  />
  );
};

const TrackingToggleLabel = ({ onToggleTrack, isTracked, canToggleTrack }) => {
  return canToggleTrack ? (
    <ToggleLabel onChange={onToggleTrack} checked={isTracked} disabled={false} />
  ) : (
    <ToggleLabel onChange={null} checked={false} disabled={true} />
  );
};

const WorkNavigation = ({
  onNext,
  onPrev,
  onChapterSelect,
  isTracked,
  onToggleTrack,
  canToggleTrack,
  currentChapter,
  chapters,
}) => {
  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: "row",
        justifyContent: "center",
        alignItems: "center",
        mx: "auto",
        boxShadow: 2,
        borderRadius: 3,
        width: "75%",
        gap: 3,
      }}
    >
      <Button sx={{ ml: 2 }} variant="contained" onClick={onPrev}>
        Prev
      </Button>
      <TextField
        id="chapter-selection"
        select
        label="Chapter"
        defaultValue={currentChapter}
        onChange={onChapterSelect}
        SelectProps={{
          native: true,
        }}
        sx={{ mx: "auto", width: "55%" }}
        variant="standard"
        margin="dense"
      >
        {chapters.map((option) => (
          <option key={option.value} value={option.value}>
            {option.label}
          </option>
        ))}
      </TextField>
      <FormGroup>
        <TrackingToggleLabel
          onToggleTrack={onToggleTrack}
          canToggleTrack={canToggleTrack}
          isTracked={isTracked}
        />
      </FormGroup>
      <Button sx={{ mr: 2 }} variant="contained" onClick={onNext}>
        Next
      </Button>
    </Box>
  );
};
export default WorkNavigation;
