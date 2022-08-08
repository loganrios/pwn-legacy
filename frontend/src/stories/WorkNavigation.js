import React from "react";
import {
  Button,
  Switch,
  Select,
  NativeSelect,
  FormControl,
  FormGroup,
  FormControlLabel,
  InputLabel,
  Box,
  TextField,
} from "@mui/material";

// Old TrackingToggle switch code. Replaced with FormControlLabel version to add proper label.
//
// const TrackingToggle = ({ onToggleTrack, isTracked, canToggleTrack }) => {
//   return canToggleTrack ? (
//     <Switch checked={isTracked} onChange={onToggleTrack} />
//   ) : (
//     <Switch checked={false} disabled={true} />
//   );
// };

const TrackingToggleLabel = ({ onToggleTrack, isTracked, canToggleTrack }) => {
  return canToggleTrack ? (
    <FormControlLabel
      control={<Switch checked={isTracked} onChange={onToggleTrack} />}
      label="Track Progress"
      labelPlacement="start"
      sx={{ mx: "auto" }}
    />
  ) : (
    <FormControlLabel
      disabled
      control={<Switch checked={false} disabled={true} />}
      label="Track Progress"
      labelPlacement="start"
      sx={{ mx: "auto" }}
    />
  );
};

const WorkNavigation = ({
  onNext,
  onPrev,
  isTracked,
  onToggleTrack,
  canToggleTrack,
  currentChapter,
  chapterNumber,
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
        Previous
      </Button>
      {/* <FormControl fullWidth> */}
      {/*   <InputLabel variant="standard" htmlFor="uncontrolled-native"> */}
      {/*     Chapter */}
      {/*   </InputLabel> */}
      {/*   <NativeSelect */}
      {/*     defaultValue={currentChapter} */}
      {/*     inputProps={{ */}
      {/*       name: "chapter", */}
      {/*       id: "uncontrolled-native", */}
      {/*     }} */}
      {/*   > */}
      {/*     <option value={10}>Ten</option> */}
      {/*     <option value={20}>Twenty</option> */}
      {/*     <option value={30}>Thirty</option> */}
      {/*   </NativeSelect> */}
      {/* </FormControl> */}
      <TextField
        id="chapter-selection"
        select
        label="Chapter"
        SelectProps={{
          native: true,
        }}
        defaultValue={currentChapter}
        inputProps={{
          name: "chapter",
          id: "uncontrolled-narrative",
        }}
        sx={{ mx: "auto", width: "55%" }}
        variant="standard"
        margin="dense"
      >
        <option value={10}>Ten</option>
        <option value={20}>Twenty</option>
        <option value={30}>Thirty</option>
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
