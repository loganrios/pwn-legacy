import React from "react";
import {
  Button,
  Switch,
  Select,
  NativeSelect,
  FormControl,
  InputLabel
} from "@mui/material";

const TrackingToggle = ({
  onToggleTrack,
  isTracked,
  canToggleTrack,
}) => {
  return canToggleTrack
    ? <Switch
      checked={isTracked}
      onChange={onToggleTrack} />
    : <Switch
      checked={false}
      disabled={true} />;
};


const WorkNavigation = ({ onNext,
  onPrev,
  isTracked,
  onToggleTrack,
  canToggleTrack,
  currentChapter,
}) => {
  return (
    <div>
      <Button
        variant="contained"
        onClick={onPrev}>
        Previous
      </Button>
      <FormControl fullWidth>
        <InputLabel variant="standard" htmlFor="uncontrolled-native">
          Chapter
        </InputLabel>
        <NativeSelect
          defaultValue={currentChapter}
          inputProps={{
            name: 'chapter',
            id: 'uncontrolled-native',
          }}
        >
          <option value={10}>Ten</option>
          <option value={20}>Twenty</option>
          <option value={30}>Thirty</option>
        </NativeSelect>
      </FormControl>
      <div>
        <p>Track Progress</p>
        <TrackingToggle
          onToggleTrack={onToggleTrack}
          canToggleTrack={canToggleTrack}
          isTracked={isTracked} />
      </div>
      <Button
        variant="contained"
        onClick={onNext}>
        Next
      </Button>
    </div >
  );
};
export default WorkNavigation;
